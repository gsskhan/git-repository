# This code sends a POST request to the Ollama API to generate a response from the specified model.
# It includes the model name, prompt, and specifies that streaming is disabled.
# The response is printed, showing either the generated text or an error message.
# Note: Ensure that the Ollama server is running and accessible at the specified URL.
# This code snippet is designed to interact with the Ollama API to generate text responses using a specified model.

import requests
import json

url = "http://localhost:11434/api/generate"
headers = {"Content-Type": "application/json"}
data = {
    "model": "gemma3:1b",
    "prompt": "Why is the sky blue?",
    "stream": False
}

response = requests.post(url, headers=headers, data=json.dumps(data))
print(response.status_code)
if response.status_code == 200:
    print("Response:", response.json())
else:
    print("Error:", response.status_code, response.text)