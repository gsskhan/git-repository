# -*- coding: utf-8 -*-
'''
Learner module main program
'''

import sys
import os
sys.path.append(os.path.abspath(os.path.join( os.path.dirname(__file__), '.' )))


def _display_initial_stmts():
    print("PYTHONPATH :" + str(sys.path))
    print("PYTHON binary location :"+sys.executable)
    pathname = os.path.dirname(sys.argv[0])
    print("Script location :"+ os.path.abspath(pathname))


if __name__ == "__main__":
    _display_initial_stmts()
    print("application completed.")
