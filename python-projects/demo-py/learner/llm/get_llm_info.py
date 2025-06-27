import requests
import json
import logging

# Configure logging
logging.basicConfig(level=logging.DEBUG)
log = logging.getLogger(__name__)

# This code sends a GET request to the Ollama API version.
log.info("Get ollama version.")
url = "http://localhost:11434/api/version"
version_response = requests.get(url)
if version_response.status_code != 200:
    log.error("Error: %s", version_response.text)
else:
    log.info("API status code: %s", version_response.status_code)
    log.info("Ollama version: %s", version_response.json())
    

# This code sends a request to get the ollama running models.
log.info("Get ollama running models.")
url = "http://localhost:11434/api/ps"
running_model_response = requests.get(url)
if running_model_response.status_code != 200:
    log.error("Error: %s", running_model_response.text)
else:
    log.info("API status code: %s", running_model_response.status_code)
    log.info("Running models: %s", running_model_response.json())

# 
log.info("Get ollama model details.")
if running_model_response.status_code == 200:
    running_models_data = running_model_response.json()
    if running_models_data.get("models"):
        for model_info in running_models_data["models"]:
            model_name = model_info.get("name")
            if not model_name:
                log.warning("Found a model in the list without a name: %s", model_info)
                continue
            
            log.info("Getting details for model: %s", model_name)
            url = "http://localhost:11434/api/show"
            headers = {"Content-Type": "application/json"}
            data = {"model": model_name}
            show_response = requests.post(url, headers=headers, data=json.dumps(data))

            if show_response.status_code == 200:
                log.info("Details for '%s': %s", model_name, show_response.json())
            else:
                log.error("Error getting details for '%s': %s", model_name, show_response.text)
    else:
        log.warning("No running models found.")


