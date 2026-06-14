## Create VENV

    $ source /opt/anaconda/anaconda3/bin/activate 

    $ python -m venv .venv

    $ exit

## Activate VENV

    $ source .venv/bin/activate

## Install dependencies

    $ pip install --upgrade pip

    $ pip install "fastapi[all]"

    $ python -m pip list

## Copy dependencies as requirement.txt

    $ pip freeze > requirements.txt
