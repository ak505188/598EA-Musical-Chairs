Tim Hughes
Frank Addelia
Alexandr Kolesnik

Remote controlled LEJOS robot. 

KeyBinding.java is ran as a java program. It sets up keybindings and sends commands to the EV3 brick. It takes two arguments the IP and the Port of the server being ran on the EV3 brick.

EV3Server.java is ran as a LEJOS program on the EV3 brick. It receives commands from the client and executes the appropriate functions.


# Project 2: Musical Chairs (Well.. Circles)
Build a mobile robot using a color (or other suitable sensors) that competes with other robots on a playing field to find a colored target on the floor and occupies it.
 
The playing field is square rectangle bounded on the sides by 2x4 lumber. Each side of the field 

Colored targets are simply black circles printed on standard copy paper with a white center circle. The black circle has a diameter of 8 inches and the white center circle has a diameter of 1 inch.

Multiple robots will be randomly placed on the field by the judge to start and the number of targets will one less than the number of participants.

Your robot's goal is to find a target as quickly as possible and sit on it. The robot closest to the center of the target wins in case of multiple robots covering the same target.

