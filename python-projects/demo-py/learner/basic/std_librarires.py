# -*- coding: utf-8 -*-
"""
Example to check python inbuilt standard libraries
"""
import glob
fname = glob.glob('*.py')
print(fname)

import os
import shutil

os.getcwd()      # Return the current working directory
os.chdir('/tmp')   # Change current working directory
# Run the command mkdir in the system shell
os.system('mkdir -p today')
os.system('touch raw_test_file.txt')
os.system('ls -lart')

# For daily file and directory management tasks, the shutil module provides a higher level interface that is easier to use:
shutil.copyfile('raw_test_file.txt', 'archive.txt')
shutil.move('archive.txt', './today/')

os.system('rm -rf today raw_test_file.txt')

# HTTP request - retrieving data from URLs
from urllib.request import urlopen
with urlopen('https://docs.python.org/') as response:
    for line in response:
        line = line.decode('utf-8')  # Decoding the binary data to text.
        print(line)

import site
p = site.getusersitepackages()
print(p)