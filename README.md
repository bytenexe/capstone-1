<div align="center"><h1>Captain Dave's Dadloon Dock</h1>
<h2>A Dad Joke "Accounting" Ledger</h2>
<h3><em>"Where every groan is gold."</em></h3><p>Hey tired, I'm Dad! Captain Dave's Dadloon Dock is a command-line ledger for tracking <s>financial</s> dad jokes transactions, written in Java 17.<br>
Dad jokes are the money here. Tell a joke and you earn Dadloons (a deposit). Spend Dadloons on boat stuff (a payment).<br>
Everything gets saved to a CSV file, so your treasure is still there when you come back.</p><p>The whole thing is themed after my instructor Dave, who sails a boat, writes code, and loves dad jokes.</p><h2>What It Does</h2><p>You can add a deposit (Haul In a Dadloon) or a payment (Pay the Harbor Master) with a date, time, vendor, and description. Type every amount as a positive number. The app turns payments negative for you.</p><p>The Captain's Log shows your transactions newest first. You can also look at just the Jokes Told (positive amounts) or just the Dadloons Spent (negative amounts).</p><p>The reports menu, which I named the Arrr-chived Dad-abase, has:</p>
<p>Month to date<br>
Previous month<br>
Year to date<br>
Previous year<br>
Search by vendor</p><p>Entries save to <code>transactions.csv</code> as you add them and load again the next time you start the app. Amounts, dates, and times get checked before they're saved, so a typo earns you a pirate scolding instead of a crash.</p><h2>Screenshots</h2><p>The startup banner (that's me and Dave on the boat):</p>
<img src="images/Red%20ASCII%20Boat%20Dock%20Illustration.png" alt="ASCII boat banner with Rosie and Dave, red characters on a black background" width="560"><p>The Dock, Captain's Log, and Arrr-chived Dad-abase menus:</p>
<img src="images/Menu_Tour_Infographic.png" alt="Illustrated menu tour of Captain Dave's Dadloon Dock, Captain's Log and Arrr-chived Dad-abase" width="700"><h2>What You Need</h2><p>Java 17 or higher<br>
Maven<br>
IntelliJ IDEA (recommended, and the boat banner gives JetBrains a shout-out)</p><h2>Running It</h2><p><strong>1.</strong> Open the project folder in IntelliJ IDEA as a Maven project.<br>
<strong>2.</strong> Open <code>src/main/java/com/pluralsight/DadloonDock.java</code> and run its <code>main</code> method.<br>
<strong>3.</strong> Follow the prompts. Talking like a pirate is optional but encouraged.</p><p>If you'd rather use a terminal, run these from the project root:</p>
<table align="center"><tr><td><pre><code>mvn compile
java -cp target/classes com.pluralsight.DadloonDock</code></pre></td></tr></table><p>Run it from the project root either way. The app looks for its data at <code>src/main/resources/transactions.csv</code>, and that path only works from there.</p><h2>How the Code Is Organized</h2><table align="center"><tr><td><pre><code>src/main/java/com/pluralsight/
    DadloonDock.java
    LogEntry.java
    CaptainsLog.java
src/main/resources/
    transactions.csv</code></pre></td></tr></table><p><code>DadloonDock</code> is the entry point and the user interface. It prints the banner and menus and asks for input.</p><p><code>LogEntry</code> is one transaction. It holds the date, time, description, vendor, and amount.</p><p><code>CaptainsLog</code> does the data work. It reads the CSV into memory, saves new entries back to the file, and hands back the lists for each ledger view.</p><h2>Data Format</h2><p>Transactions are stored in <code>src/main/resources/transactions.csv</code>. Each line is one transaction, with the fields separated by a pipe (<code>|</code>), so keep pipes out of your puns. The first line is a header row:</p><table align="center"><tr><td><pre><code>date|time|description|vendor|amount
2026-10-01|09:12:04|Why do Java developers wear glasses? Because they can't C#|Dave|5.00
2026-10-03|18:30:45|I'd tell you a UDP joke, but you might not get it|Dave|3.00
2026-10-06|11:05:20|Marina Berth Fee|Harbor Master|-12.00
2026-10-09|14:22:41|What is a pirate's favorite programming language? It's the C|Dave|8.00
2026-10-11|10:30:00|Spanish Lessons (no return on investment)|Language School|-5.00</code></pre></td></tr></table><p><code>date</code> is <code>YYYY-MM-DD</code><br>
<code>time</code> is <code>HH:MM:SS</code><br>
<code>description</code> is the joke, or what the payment was for<br>
<code>vendor</code> is who told the joke, or who got paid<br>
<code>amount</code> is positive for a deposit and negative for a payment</p><h2>Chartin' Our Next main()-euver</h2>
<p><em>(Next Steps / Updates)</em></p><h3>Groan Levels</h3><p>Right now you type in how many Dadloons a joke is worth. Next, I want to replace that with a Groan Level, borrowed from the wind scale sailors use. You'd rate how bad the joke was from 1 to 5, and the worse it is, the more Dadloons it earns: 1, 3, 5, 8, or 12. A level 5 prints <strong>HURRICANE WARNING: NEW SUPPLY MINTED.</strong> 
<p> SPANGLISH BONUS! A joke told in attempted Spanish gets a +1 Dadloon Spanglish Bonus.</p><img src="images/Groan%20Level%20Pirate%20Infographic.png" alt="Groan Level infographic: Light Breeze 1, Fresh Wind 3, Gale 5, Storm 8, Hurricane 12 Dadloons, plus Level 5 warning and Spanglish Bonus" width="700"><h3>Web Edition</h3><p>There's also a web version: <a href="https://capn-daves-dock.base44.app/">capn-daves-dock.base44.app</a></p><p>It's fully functional, minus a few bugs and unresponsive buttons. You can deposit a dad joke, spend some fake booty, and watch the Treasure Chest balance and the Captain's Groan Account update.</p><img src="images/Dadloon_Dock_Walkthrough_3x.gif" alt="Walkthrough of the Dadloon Dock website" width="700"><h3>How the Code Works</h3><p>This GIF follows one trip through the code, from startup to saving an entry to printing the ledger.</p><img src="images/dadloondock_code_verified_corrected.gif" alt="Walkthrough of how DadloonDock, CaptainsLog, LogEntry, and transactions.csv work together" width="700"><p>Animated walkthrough of how DadloonDock, CaptainsLog, LogEntry, and transactions.csv work together.</p><details>
<summary>The same trip as text</summary>
<p>1. Startup: <code>DadloonDock</code> creates <code>CaptainsLog</code>.<br>
2. <code>CaptainsLog</code> calls <code>loadTransactions()</code>.<br>
3. CSV rows become <code>LogEntry</code> objects in memory.<br>
4. Startup finishes and <code>DadloonDock</code> shows the menu.<br>
5. A deposit or payment creates a new <code>LogEntry</code>.<br>
6. <code>DadloonDock</code> now holds the <code>newEntry</code> object.<br>
7. <code>DadloonDock</code> calls <code>captainsLog.addEntry(newEntry)</code>.<br>
8. <code>CaptainsLog</code> adds it to memory and saves the CSV.<br>
9. <code>saveTransactions()</code> finishes writing the file.<br>
10. <code>DadloonDock</code> goes back to the menu.<br>
11. The ledger menu calls <code>getAll()</code> or a filtered getter.<br>
12. <code>CaptainsLog</code> returns a <code>List</code> of <code>LogEntry</code> objects.<br>
13. <code>DadloonDock</code> prints the entries.</p>
</details><h2>Credits</h2>
<p>Built by Rosie, inspired by Captain Dave.</p></div>