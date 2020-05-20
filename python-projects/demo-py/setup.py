'''
Created on 23-Sep-2019

@author: gsskhan
'''

import setuptools

with open("README.md", "r") as fh:
    long_description_text = fh.read();

setuptools.setup(
     name='demopy',
     version='0.1',
     author="G S S KHAN",
     author_email="gsskhan@gmail.com",
     description="A demo project",
     long_description=long_description_text,
     url="https://github.com/gsskhan/git-repository/tree/master/demo-py",
     packages=setuptools.find_packages(),
     classifiers=[
         "Programming Language :: Python :: 3",
         "License :: OSI Approved :: MIT License",
         "Operating System :: OS Independent",
     ]
 )
