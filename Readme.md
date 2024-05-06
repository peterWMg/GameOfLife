# Running the Game
The Game of Life can be executed packaging with:  
    **mvn clean package**  
and then running the jar file with the appropriate input:  
    **java -jar game-1.0.0.jar "[[1,1]]"**

# Solution
The solution uses a LinkedHashMap store just the live cells. The choice of Linked Hash Map
was made as the given example output indicated the order of processing was determined by the
order of input and a LinkedHashMap facilitates this by returning elements in the order they are
added.  The Hash part was used to enable quick checking if a cell is alive or dead.

The order of operation to satisfy the correct output is
<ol>
<li>Iterate over just the live cells in the order they were supplied and</li> 
<li>checking the current alive cell for the next generation status and adding to the next generation if alive.</li>  
<li>After each live cell was processed the program checks the dead neighbours for next generation status and
adds them to the next generation if alive.</li>
<li>The execution is then sent back to step 1 to start with the next live cell.</li>
</ol>

In a real application I would put more helpful return information instead of throwing the IllegalArgumentException.
The only check was when the first input was included as an example of how this would be handled.

I have included some basic application tests.  They are not comprehensive and are not unit testing using 
mocked objects but the addition of such tests to test the logic paths in real application would be expected.

The solution to the problem would vary depending on the requirements for speed or memory constraints or even 
for processing a much larger grid of cells.  As mentioned the solution here was mainly driven by the provided 
output example.