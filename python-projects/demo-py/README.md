Demo py
=======

A simple python 3 based project for demo work 

Compiling Your Package
======================

Go into your package folder and execute this command: python3 setup.py bdist_wheel

Example:
$ export PYTHONPATH=/media/gsskhan/WORK/git-repository-local/git-repository/demo-py

$ python3 setup.py bdist_wheel


Install on Your Local Machine
=============================

If you want to test your application on your local machine, you can install the .whl file using pip. The .whl file is generated inside distribution ("dist") folder. Command: python3 -m pip install dist/<The generated wheel file>.whl

Note: Never run above command with root/sudo

Example:
$ python3 -m pip install dist/demopy-0.1-py3-none-any.whl

	Processing ./dist/demopy-0.1-py3-none-any.whl
	Installing collected packages: sihir
	Successfully installed demopy-0.1

The package got installed at following location for myself: /home/gsskhan/.local/lib/python3.6/site-packages

Note: Packages installed by Pure distutils packages, the pip unistall command is not able to remove/delete them.

PyDev Settings
==============

The default .pydevproject file

	<?xml version="1.0" encoding="UTF-8" standalone="no"?>
	<?eclipse-pydev version="1.0"?>
	<pydev_project>
	<pydev_pathproperty name="org.python.pydev.PROJECT_SOURCE_PATH">
		<path>/${PROJECT_DIR_NAME}</path>
	</pydev_pathproperty>
	<pydev_property name="org.python.pydev.PYTHON_PROJECT_VERSION">python interpreter</pydev_property>
	<pydev_property name="org.python.pydev.PYTHON_PROJECT_INTERPRETER">Default</pydev_property>
	</pydev_project>
