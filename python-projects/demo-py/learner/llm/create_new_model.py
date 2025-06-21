# This code sends a POST request to the Ollama API to create a new model.
# It includes the model name, prompt, and specifies that streaming is disabled.
# The response is printed, showing either the generated text or an error message.
# Note: Ensure that the Ollama server is running and accessible at the specified URL.
# This code snippet is designed to interact with the Ollama API to create new models using a specified model.

import requests
import json

url = "http://localhost:11434/api/create"
headers = {"Content-Type": "application/json"}
data = {
    "model": "javawiz",
    "from": "gemma3:1b",
    "system": "Your name is Java wizard, you are a Java programmer and you create Java programs like console and web GUI applications.",
    "parameters": {
        "temperature": 0.5
    },
    "stream": False
}

response = requests.post(url, headers=headers, data=json.dumps(data))
print('Staus code = ',response.status_code)
print('Response text = ', response.text)