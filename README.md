# myBluePrintQATest
<strong>Automation Test developed for myBluePrint ca</strong>

<UL><b>Pre-requisites:</b>
  <li>JDK 1.8</li>
<li>Maven</li>
<li>allure</li>
  </ul>

<ul><b>You can run it through either command line or Eclipse IDE.</b>

<li>1. Just Clone the directory and fire a command in command prompt: "<b>mvn clean test verify</b>" in case you want to execute tests and open generated report by default in a temp directory to your system</li>
<p>OR</p>
<li>2. You have to install "allure for command line" from here: https://docs.qameta.io/allure/#_commandline and then you can fire command "<b>mvn clean test allure:report</b>" and then "<b>allure open reports/allure-report</b>" and it will open the report generated to "reports/allure-report" folder relative to your cloned test project directory.</li>
</ul>

<ul><li><b>POM.XML</b> has all the information which used in generating reports, cleaning directories before each run, dependencies for project, etc.</li>

<li><b>run.bat</b> file used explicitly called from maven so that user not have to open it separately to see report generated in temp directory.</li></ul>

<p>For your convenience I've kept a folder "<b>reports/allure-report</b>" in this project which is genereated report itself, but to see it you have to use option 2 above which is, do installation of "allure" from the given link and just fire command: "<b>allure open reports/allure-report</b>".</p>

<p>Also I have added Test Execution Video:  "<a href="https://github.com/rohinegi548/myBluePrintQATest/blob/master/myBluePrintTestExecutionVideo.mp4">myBluePrintTestExecutionVideo.mp4</a>". 
  <br><br>You can also look at other Execution Videos, basically a demo for you w.r.t <b><br>"E2E API Test Automation": <a href="https://github.com/rohinegi548/myBluePrintQATest/blob/master/E2E-APIAutomationAndReportingDemo.mp4">E2E-APIAutomationAndReportingDemo.mp4</a> <br><br>"E2E Test Automation Using RobotFramework": <a href="https://github.com/rohinegi548/myBluePrintQATest/blob/master/E2E-TestAutomationAndReportingDemo.mp4">E2E-TestAutomationAndReportingDemo.mp4</a>. <br><br><b><i>These are developed by me</i></b>.
  <br>
<br>
  <b><i>code for above API Test Automation and Robot Framework Not yet uploaded on Git.</i></b>
</p>

<p><b>Also I urge you to have a glance at my other repositories which you can look at these links below:
  <ul>
    <li><a href="https://github.com/rohinegi548">My Pinned Repositories</a></li>
  </ul>
These will give you a glance about my capabilities and experience I can add in your team. I just used TestNG and basic concepts to deliver/develop this coding test which doesn't include page object model, etc but that doesn't mean I don't know. This is just because of the time crunch I'm having this time due to multiple releases.</b></p>

<p>For your convenience I've attached some of the screenshot of allure reports here:</p>

<ul><li> Report snaphost1: https://ibb.co/NryrnyN</li>
<li> Report snaphost2: https://ibb.co/D8hFXg7</li>
<li> Report snaphost3: https://ibb.co/zr1KcLK</li>
<li> Report snaphost4: https://ibb.co/RGKWzBc</li>
  </ul>
