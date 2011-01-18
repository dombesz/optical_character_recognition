**Optical character recognition**
==============================
This program illustrates the pattern recognition capabilities of the multi-layer perceptron with an optical character recognition application. The program has a learning database which contains the matrix representations of the digits from 0-9. After learning the user can draw digits in the application  and the program will try to recognize that digit.

##The controls

1.  ###Network initialization.
Initialisation of the network can be done with the following panel.
	
![layer-setup](https://github.com/dombesz/optical_character_recognition/raw/master/images/image1.png)

	
In this panel we can set the number of the neurons on the hidden layers. For example in this case we set the network with one hidden layer which contains 10 neurons.

2.  ###Loading samples and teaching network.

![Learning graph](https://github.com/dombesz/optical_character_recognition/raw/master/images/image2.png)


We can load samples to the network by pressing the load button. After loading samples we can set the parameters like momentum, learning rate and the number of iterations. 
The learn button will starts the network to learn and as a result we can see the error on the graph, in the function of iterations. We can press the learn button more times and we can see the results of the learning iterations on the graph.

3.  ###Testing the knowledge.

![Character drawer](https://github.com/dombesz/optical_character_recognition/raw/master/images/image3.png)
  
To test the knowledge of the network first we need to draw a number in the square with the mouse. After we draw a number we should press the Test button and we will see the results of the recognition. As a result we can see a percent estimation to each character, and the number with the highest probability is marked with red color.