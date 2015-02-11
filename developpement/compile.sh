#!/bin/bash

scalac -sourcepath src/ -d classes/ \
    src/generateurLabyrinthe/GenerateurLabyrinthe.scala \
    src/graph/Graph.scala
