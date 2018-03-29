#!/bin/bash

mkdir Teksty 
mkdir Programy
mkdir Inne

mv *.txt /Teksty
mv *.c /Programy
mv * /Inne

chmod 600 Teksty/*
chmod 600 Programy/*
chmod 400 Inne/*

chmod 500 Teksty
chmod 500 Programy
chmod 500 Inne
