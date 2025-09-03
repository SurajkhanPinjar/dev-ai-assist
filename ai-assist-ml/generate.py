from peft import LoraConfig, get_peft_model
from transformers import (
    AutoModelForCausalLM,
    AutoTokenizer,
    TrainingArguments,
    Trainer,
    DataCollatorForLanguageModeling
)
from datasets import Dataset

# ==============================
# 🔹 Step 1: Prepare Dataset
# ==============================
data = {
    "text": [
        "Generate JUnit tests for addUser method",
        "Explain code quality improvements for removeUser",
    ]
}
dataset = Dataset.from_dict(data)

# ==============================
# 🔹 Step 2: Load Base Model & Tokenizer
# ==============================
model_name = "TinyLlama/TinyLlama-1.1B-Chat-v1.0"

tokenizer = AutoTokenizer.from_pretrained(model_name)
# Add a PAD token if missing (needed for batching)
if tokenizer.pad_token is None:
    tokenizer.add_special_tokens({'pad_token': '[PAD]'})

model = AutoModelForCausalLM.from_pretrained(model_name)
model.resize_token_embeddings(len(tokenizer))  # Resize token embeddings

# ==============================
# 🔹 Step 3: Configure LoRA
# ==============================
lora_config = LoraConfig(
    r=8,
    lora_alpha=16,
    target_modules=["q_proj", "v_proj"],  # Apply LoRA to attention layers
    lora_dropout=0.1,
    bias="none",
    task_type="CAUSAL_LM"
)

model = get_peft_model(model, lora_config)

# ==============================
# 🔹 Step 4: Tokenize Dataset
# ==============================
def tokenize(batch):
    tokens = tokenizer(batch["text"], padding="max_length", truncation=True, max_length=64)
    tokens["labels"] = tokens["input_ids"].copy()  # Needed for causal LM loss
    return tokens

tokenized_dataset = dataset.map(tokenize, batched=True)

# ==============================
# 🔹 Step 5: Data Collator
# ==============================
data_collator = DataCollatorForLanguageModeling(tokenizer, mlm=False)

# ==============================
# 🔹 Step 6: Training Config
# ==============================
training_args = TrainingArguments(
    output_dir="./lora-tiny-llama",       # Save model here
    per_device_train_batch_size=1,        # Small batch size for MacBook
    gradient_accumulation_steps=2,
    warmup_steps=5,
    max_steps=20,                         # Quick test training
    learning_rate=2e-4,
    fp16=False,                           # Disable mixed precision (no GPU)
    logging_steps=5,
    save_steps=20,
    save_total_limit=1
)

# ==============================
# 🔹 Step 7: Trainer Setup
# ==============================
trainer = Trainer(
    model=model,
    args=training_args,
    train_dataset=tokenized_dataset,
    data_collator=data_collator
)

# ==============================
# 🔹 Step 8: Train
# ==============================
trainer.train()
print("✅ LoRA Fine-tuning Complete! Model saved at ./lora-tiny-llama")