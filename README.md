# Camera2Video1
*This is not a Google Product*
*This Applications was created by Michael Stewart and Min Jae Kim under the supervision of Hakki Can Karamier, Abdelrahman Kamel and Professor Michael Brown at York University.*

Sister Application: https://github.com/minjaekim96/camera2_learn

# Updates
- Fixed small bugs


# Purpose
The goal of this project was to create an application that could manipulate the Digital Imaging Pipeline using Android's Camera 2 API system.The Digital Imagine Pipeline Model was based off of Hakki Can Karamier's work in ["A Software Platform for Manipulating the Camera Imaging Pipeline"](https://karaimer.github.io/camera-pipeline/).
 Exploring the pipeline could lead to advances in computer vision, aesthetic photography, and many more.  

# Features
 - JPEG,RAW and PNG Capture
 - Preset and Custom White Balance Options
 - Speccial Effects
 - Scene Modes
 - Resolution Options
 - Real Time Info
 - ISO and Shutter Speed Options
 - Video Capture
 - Timelapse and Intervalometer
 - *Export Digital Imaging Steps* *
# The Digital Imaging Pipeline
As seen below, the digital imaging pipeline follows a 12 steps process. Each steps has a function applied to it.  *Due to Camera2 API abilities to access this pipeline we were able to isolate steps 1(RAW Image),2(Black Light Subtraction), and 6 (White Balancing)apply demoisiacing to them and export them as PNG files. 
[![N|Solid](https://karaimer.github.io/camera-pipeline/image/Fig_02_pipeline_figure_final.png)](https://karaimer.github.io/camera-pipeline/)


# Known Bugs
- Application glitches out after you take more than 5 pictures
- Resolutions are not always compatable with the phone
- Not yet optimized for all devices

# Future Plans
As of right now we are done coding this application due to schooling. We hope that someday an application like this can access all steps of the pipeline. 



# Contributing
All contributes to this project must be made through GitHub's pull module

See [CONTRIBUTING.md](https://github.com/mstewart2525/Camera2Video1/blob/master/CONTRIBUTING.md)
