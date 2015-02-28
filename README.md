# Fire-Work-Simulation-using-Spatial-Database

## Scenario
In case of fire happening in the campus, we need a system to keep track of all the fire hydrants, buildings and the buildings that are on fire. Each fire hydrant is represented as a point and each building is represented as a polygon.

## Input Files
1. Image file: MAP -­­ an 820x580 JPEG file that is an image of some area.
2. Following input files:
  * building.xy. Each building is represented by a 2D polygon. Col 1: building ID. Col 2: building name. Col3: number of vertices on the polygon. The numbers after column 3 are the coordinates of the vertices. They are ordered as , , , , ...., , . For example, a row: b1, PHA, 4, 100, 120, 150, 130, 120, 200, 120, 220 represents a building with its building ID as "b1" and its name as "PHA". It has 4 vertices whose coordinates are (100, 120), (150, 130), (120, 200) and (120, 220) respectively.
  * firehydrant.xy. Col 1: firehydrantID Col2: x coordinate of the firehydrant location. Col3: y coordinate of the firehydrant location.
  * firebuilding.txt. Col 1: firebuilding represents the building name, which is on fire.

## Supported Queries
1. Whole Region. This is to display all the features of the active feature types in the whole map.
2. Range Query. When this radio button is checked, the user can draw a polygon in the map. After pushing the Submit Query button, only the features of the active feature types that are inside (or intersect with) the polygons are displayed. The user draws the polygon by clicking the left mouse button to select its vertices sequentially and then clicking the right mouse button to close the polygon.
3. Find neighbor Buildings. When this radio button is checked, the buildings on fire are displayed as Red polygons. You should also display the neighbor buildings in Yellow polygons. The neighbor buildings are the buildings that are located within 100 pixels. The people in those neighbor buildings also need to be evacuated to safe places.
4. Find Closest Fire Hydrants. When this radio button is checked, the user could use click to specify arbitrary number of buildings that are on fire. The buildings are shown as red polygons and the closest hydrants are displayed as Green Squares. If there are multiple buildings on fire, two buildings on fire might have the same closest fire hydrant. The closest fire hydrants shown on the GUI will provide the firefighter essential information to locate and secure the water sources.

## How to compile and run the code

The zip file contains a directory named mainProgram and a script file run.sh which can help you compile/run the program simply. There are five files in the directory: createdb.sql, dropdb.sql, Populate.java, HW3.java and HW3GUI.java.

Firstly you should unzip it and run createdb.sql in sqlplus to create necessary tables and indexes.

Then assume the current directory that contains directory mainProgram and run.sh is named CurDir. You should put map.jpg into CurDir.

Next, open run.sh and replace the file paths including ojdbc6.jar, sdoapi.jar, building.xy, hydrant.xy and firebuilding.txt with your own.

Run the script file in directory CurDir e.g. sh run.sh or . run.sh

NOTE: for the sake of security, all host url, user name and password have been removed. Please fill in these paramters in HW3.java and Populate.java's getConnection() method if you want to run it really.

## Supported Platform

The script is only run and tested in Linux and Mac OS. Technically you can also run it in Windows as long as a few modifications are made.
