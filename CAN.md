CAN Bus
=======

The CAN bus uses a daisy-chain of two wires,
usually a twisted pair of green and yellow,
to connect up to about 64 devices of one type to the roboRIO.
Each device has a CAN ID, a number from 0 to 63.

The chain starts at the roboRIO, runs to the first device,
on to the next device and so on.
The end of the bus must be _terminated_ with a 120 Ohm resistor.
The Power Distribution Panel includes such a termination resistor,
so the PDP must be at the __end__ of the daisy-chain
and the PDP termination must be enabled via a jumper
located next to its CAN connector.
If there is no PDP at the end, you need to terminate the bus
by adding a 120 Ohm resistor.

The WPIlib includes several classes for CAN-based devices.
You can put those marked as [DB] onto the SmartDashboard to display their state or operate them in teleop.

 * `PowerDistributionPanel`: Get info from PDP [DB]
 * `Solenoid`: One of the Pnematic Control Module (PCM) outputs [DB]
 * `DoubleSolenoid`: Similar, controls 2 outputs at once [DB]
 * `Compressor`: PCM status info [DB]
 * `CAN`: Would allow reading/writing raw data to a CAN device.

In principle you could use the PDP and PCM without further ado,
except that their CAN IDs default to 0, so you could only use one of them.

To program CAN IDs, monitor the state of the CAN bus, and to get support
for additional CAN devices like motor controllers, you need to install the
Cross-The-Road-Electronics "Phoenix" software,
https://phoenix-documentation.readthedocs.io/en/latest/index.html

 * Download / unzip CTRE Phoenix Framework ZIP and run the *.exe.
   It should auto-select Lifeboard, Tuner, C++/Java.
 * Start the CTRE Phoenix Tuner via desktop link
   (Should be in
    C:\Users\Public\Documents\Cross The Road Electronics\Phoenix-Tuner)
 * Use it to install the Phoenix Library / Diagnostics on the roboRIO.

Now you can configure your CAN devices:

 1) Connect the roboRIO CAN bus to the Power Distribution Panel
    (with termination enabled), and use the Tuner to set its CAN ID.
 2) Change the CAN bus to go from the roboRIO to the Pneumatics Control Module,
    and then from there to the PDP.
    Now use the Tuner to set the PCM's CAN ID.
 3) Add more devices, one at a time, to set their CAN ID.


To use CTRE devices like `TalonSRX` in Java code
(that's already been done for this software):

 1) Right-click on the file `build.gradle`
 2) Select "Manage Vendor Libraries"
 3) Select "Install new libraries (offline)"
 4) Select "CTRE-Phoenix"

 Examples are in https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/tree/master/Java

To later __update__ the Phoenix software, download and install the latest
similar to the initial installation.
Then follow the same "Manage Vendor Libraries" procedure as before
but select "Check for updates (offline)" and select the new version.

Troubleshooting
---------------

 * When power is off, the CAN bus should measure a resistance of 60 Ohm
   when both ends are properly terminated.
   If you measure 120 Ohm, that's the termination on the roboRIO side,
   and the other end of the bus is not terminated, or some daisy-chain
   hop is broken.

 * Test each device individually, so it's the only device on the bus.
   With a single device and short wires, you can skip the termination.

 * Update device firmware via Tuner.
   Firmware files are in C:\Users\Public\Documents\FRC

 * User Tuner to run "Self Test" on each device.