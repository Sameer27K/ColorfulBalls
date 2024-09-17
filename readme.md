Bouncing Balls with Smiley Faces

An interactive Java program where colorful smiley-faced balls bounce around the screen, collide with each other, and react to gravity. Users can adjust gravity and particle effects in real-time using sliders, pause or resume the simulation, and reset the environment. The balls leave trails and generate sparkles upon collision, creating an engaging visual experience.

Features
- Interactive Simulation: Click anywhere on the window to spawn a new smiley-faced ball.
- Physics Simulation: Balls bounce off walls and each other, simulating gravity and collisions.
- Real-time Controls:
  - Gravity Slider: Adjust the gravity affecting the balls.
  - Particle Intensity Slider: Control the intensity of sparkles generated during collisions.
Visual Effects:
  - Smiley Faces: Each ball displays a smiley face for added cuteness.
  - Trails: Balls leave fading trails behind them.
  - Sparkles: Particle effects when balls collide or bounce off walls.
  - Gradient Background: A dynamic gradient background for visual appeal.
  - Audio: Background music plays during the simulation, enhancing the experience.
Controls:
  - Pause: Pause the simulation and music.
  - Resume: Resume the simulation and music.
  - Reset: Reset the simulation, clearing all balls and particles, and restarting the music.

Getting Started

Prerequisites
Java Development Kit (JDK) 8 or higher: Ensure you have the JDK installed. You can download it from Oracle's official site or use OpenJDK.

Audio File: A background music file in WAV format named background_music.wav.

Project Structure
BouncingBalls/
├── BouncingBalls.java
├── Ball.java
├── Particle.java
├── Trail.java
├── MusicPlayer.java
├── resources/
│   └── background_music.wav
├── screenshots/
│   └── bouncing_balls_screenshot.png
└── README.md

Installation
1. Clone the repository:
git clone https://github.com/yourusername/BouncingBalls.git
cd BouncingBalls

Place the Background Music File
- Ensure you have a background_music.wav file.
- Place it inside the resources folder.

Compilation
Open a terminal or command prompt in the project directory and run:

javac *.java

This command compiles all Java files in the directory.

Running the Program
After successful compilation, run:

java BouncingBalls

Usage
- Spawn Balls: Click anywhere within the window to spawn a new smiley-faced ball at that location.
- Adjust Gravity: Use the Gravity slider to increase or decrease the gravitational pull on the balls.
- Adjust Particle Intensity: Use the Particle Intensity slider to control the number of sparkles generated during collisions.
Pause/Resume:
- Click Pause to pause the simulation and music.
- Click Resume to continue the simulation and music.
- Reset: Click Reset to clear all balls and particles, and restart the background music.

Customization
Changing the Background Music
- Replace the background_music.wav file in the resources folder with your preferred music.
- Ensure the file is in WAV format for compatibility.

Modifying Gradient Colors
In BouncingBalls.java, locate the paintComponent method.

Adjust the GradientPaint colors to your desired start and end colors:

GradientPaint gp = new GradientPaint(
    0, 0, new Color(R1, G1, B1), // Start color
    getWidth(), getHeight(), new Color(R2, G2, B2) // End color
);

Replace R1, G1, B1, R2, G2, B2 with RGB values ranging from 0 to 255.

Adjusting Ball Features
- Ball Size: Currently set to a fixed size for consistent smiley faces. You can modify size in the Ball class constructor.
- Smiley Faces: Customize the facial expressions by adjusting the drawing code in the draw method of the Ball class.

Troubleshooting
Common Issues
- javac or java Command Not Found: Ensure the JDK is correctly installed and the environment variables are set.
- Audio Playback Issues: The program requires a WAV audio file. If you're using a different format, convert it to WAV or adjust the MusicPlayer class to handle other formats.
- Performance Problems: If the program runs slowly:
  - Reduce the Particle Intensity using the slider.
  - Limit the number of balls spawned.
  - Close other applications that may be consuming system resources.

Dependencies
- Java Swing: For GUI components.
- Java AWT: For drawing graphics.
- Java Sound API: For audio playback.

All dependencies are part of the standard Java library; no external libraries are required.

Acknowledgments
- Inspired by classic bouncing ball animations and physics simulations.
- Special thanks to the Java development community for providing valuable resources and support.