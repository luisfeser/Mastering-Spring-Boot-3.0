#!/bin/bash
TOKEN=$(cat token.txt)
http "$@" Authorization:"Bearer $TOKEN"