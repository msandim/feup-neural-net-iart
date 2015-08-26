# Backpropagation Artificial Neural Network in Java

This repository contains a Artificial Backpropagation Multilayer Neural Network implementation developed on the "Artificial Intelligence" course at FEUP.

The goal was to build an efficient Neural Network, using the Backpropagation algorithm in the learning process, to diagnose patients from a dataset based on their voice's physical characteristics.

Application developed by 
- [JPMMaia](https://github.com/JPMMaia)
- [msandim](https://github.com/msandim)

## 1. Project structure

- "data" - contains the train and test data;
- "src" - source code of the application;
- "test" - unit tests used in the development phase;
- "uml" - class diagram of the application.

Run note: The main function is included in "GUIMain.java". The main function in the "Program.java" was only used for development debugging.

## 2. Dataset

The dataset involved in this project (including training and testing sets) is described and available for download [here](https://archive.ics.uci.edu/ml/datasets/Parkinson+Speech+Dataset+with++Multiple+Types+of+Sound+Recordings) and includes several features from patient voice recordings (vowels, numbers, short sentences and words). The "UPDRS" feature was ignored in this analysis.

## 3. Learning algorithm

In order to find the connection weights that minimize the model's cost function, the Backpropagation algorithm was used. The minimization step is based on the Gradient Descent algorithm, with an added "momentum" term that avoids imprisonments in local minimums of the cost function (which may not be convex).

More information on this algorithm is available here:
> Fausett, Laurene. Fundamentals of neural networks: architectures, algorithms, and applications. Prentice-Hall, Inc., 1994.

## 4. Graphical User Interface

![gui](https://github.com/msandim/neural-net-iart/blob/master/imgs/gui.png?raw=true)

The interce allows the user to modify the:
* Number of neurons on each hidden layer;
* Learning rate and momentum values;
* Train and test data paths;
* Medium Squared Error value to achieve convergence in the algorithm;
* Maximum number of iterations;
* types of recordings present on the train and test phases.

On the right panel the MSE for the training set on each iteration is showed, along with the number of well-classified cases. After the training process is concluded, the same information is reported for the test set.
