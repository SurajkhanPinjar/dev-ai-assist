from transformers import AutoModelForCausalLM, AutoTokenizer
from peft import PeftModel
import torch

# 🔹 Base model
model_name = "TinyLlama/TinyLlama-1.1B-Chat-v1.0"
adapter_path = "./lora-tiny-llama/checkpoint-20"  # ✅ Correct path

# 🔹 Load tokenizer
tokenizer = AutoTokenizer.from_pretrained(model_name)
if tokenizer.pad_token is None:
    tokenizer.add_special_tokens({'pad_token': '[PAD]'})

# 🔹 Load base model
base_model = AutoModelForCausalLM.from_pretrained(model_name)
base_model.resize_token_embeddings(len(tokenizer))

# 🔹 Load LoRA model
model = PeftModel.from_pretrained(base_model, adapter_path)

# 🔹 Test prompt
prompt = "Write JUnit tests for getAllUsers method in Java"
inputs = tokenizer(prompt, return_tensors="pt")

# 🔹 Generate response
with torch.no_grad():
    outputs = model.generate(**inputs, max_new_tokens=80)

print("\n🤖 LoRA Model Response:\n")
print(tokenizer.decode(outputs[0], skip_special_tokens=True))