# Backpropagation Artificial Neural Network in Java

This repository contains a Artificial Backpropagation Multilayer Neural Network implementation developed on the "Artificial Intelligence" course at FEUP.

The goal was to build and efficient Neural Network, using the Backpropagation algorithm in the learning process, to diagnose patients from a dataset based on their voice physical characteristics.

## 1. Project structure

Run note: The main function ins included in "GUIMain.java". The main function in the "Program.java" was only used for development debugging.

## 2. Dataset

The dataset involved in this project (including a training and testing sets) is described and available for download [here](https://archive.ics.uci.edu/ml/datasets/Parkinson+Speech+Dataset+with++Multiple+Types+of+Sound+Recordings) and includes several features from patient voice recordings (which include vowels, numbers, short sentences and words). The "UPDRS" feature was ignored in this analysis.

## 3. Learning algorithm

In order to find the connection weights that minimize the cost function of the model, the Backpropagation algorithm was used. The minimization step is based on the Gradient Descent algorithm, with an added "momentum" term that avoids imprisonments in local minimums of the cost function (which may not be convex).

More information on this algorithm is available here:
> Fausett, Laurene. Fundamentals of neural networks: architectures, algorithms, and applications. Prentice-Hall, Inc., 1994.

## 4. Graphical User Interface

![1](https://github.com/msandim/neural-net-iart/blob/master/imgs/gui.png?raw=true)
