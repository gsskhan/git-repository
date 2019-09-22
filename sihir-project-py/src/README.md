sihir
=====

A simple python program for AI

The code is Python 3, but Python 2 compatible.

Compiling Your Package
======================

Go into your package folder and execute this command: python3 setup.py bdist_wheel

Example:
$ export PYTHONPATH=/media/gsskhan/WORK/git-repository-local/git-repository/sihir-project-py/src

$ python3 setup.py bdist_wheel

	running bdist_wheel
	running build
	running build_py
	installing to build/bdist.linux-x86_64/wheel
	running install
	running install_lib
	creating build/bdist.linux-x86_64/wheel
	creating build/bdist.linux-x86_64/wheel/sihir_root
	copying build/lib/sihir_root/app_main.py -> build/bdist.linux-x86_64/wheel/sihir_root
	copying build/lib/sihir_root/app_service.py -> build/bdist.linux-x86_64/wheel/sihir_root
	copying build/lib/sihir_root/common_conf.py -> build/bdist.linux-x86_64/wheel/sihir_root
	copying build/lib/sihir_root/common_utility.py -> build/bdist.linux-x86_64/wheel/sihir_root
	copying build/lib/sihir_root/custom_exception.py -> build/bdist.linux-x86_64/wheel/sihir_root
	copying build/lib/sihir_root/db_access.py -> build/bdist.linux-x86_64/wheel/sihir_root
	copying build/lib/sihir_root/pre_checks.py -> build/bdist.linux-x86_64/wheel/sihir_root
	copying build/lib/sihir_root/__init__.py -> build/bdist.linux-x86_64/wheel/sihir_root
	running install_egg_info
	running egg_info
	writing sihir.egg-info/PKG-INFO
	writing dependency_links to sihir.egg-info/dependency_links.txt
	writing top-level names to sihir.egg-info/top_level.txt
	reading manifest file 'sihir.egg-info/SOURCES.txt'
	writing manifest file 'sihir.egg-info/SOURCES.txt'
	Copying sihir.egg-info to build/bdist.linux-x86_64/wheel/sihir-0.1.egg-info
	running install_scripts
	creating build/bdist.linux-x86_64/wheel/sihir-0.1.dist-info/WHEEL
	creating '/media/gsskhan/WORK/git-repository-local/git-repository/sihir-project-py/src/dist/sihir-0.1-py3-none-any.whl' and adding '.' to it
	adding 'sihir_root/__init__.py'
	adding 'sihir_root/app_main.py'
	adding 'sihir_root/app_service.py'
	adding 'sihir_root/common_conf.py'
	adding 'sihir_root/common_utility.py'
	adding 'sihir_root/custom_exception.py'
	adding 'sihir_root/db_access.py'
	adding 'sihir_root/pre_checks.py'
	adding 'sihir-0.1.dist-info/DESCRIPTION.rst'
	adding 'sihir-0.1.dist-info/metadata.json'
	adding 'sihir-0.1.dist-info/top_level.txt'
	adding 'sihir-0.1.dist-info/WHEEL'
	adding 'sihir-0.1.dist-info/METADATA'
	adding 'sihir-0.1.dist-info/RECORD'


Install on Your Local Machine
=============================

If you want to test your application on your local machine, you can install the .whl file using pip. The .whl file is generated inside distribution ("dist") folder. Command: python3 -m pip install dist/<The generated wheel file>.whl

Note: Never run above command with root/sudo

Example:
$ python3 -m pip install dist/sihir-0.1-py3-none-any.whl 

	Processing ./dist/sihir-0.1-py3-none-any.whl
	Installing collected packages: sihir
	Successfully installed sihir-0.1

The package got installed at following location for myself: /home/gsskhan/.local/lib/python3.6/site-packages

Note: Packages installed by Pure distutils packages, the pip unistall command is not able to remove/delete them.
