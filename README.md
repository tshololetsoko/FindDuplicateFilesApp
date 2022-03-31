Overview
_________________________________________
This repo contains an app that finds duplicate files in a given system. 
This is done by comparing the content of the file and get stored in a list when 2 files or more files are matching.

Solution Explanation
__________________________________________
The following collections was used when writing the solution (in class FindDuplicateFiles):
1. Hashmap - used to store the content as the key and add the absolute path as a Linked List. If the file content is the same the given Hashmap will have 2 or more list. ashmap also ensures that there are duplicate key
2. LinkedList - used to store the absolute path. Compared to an Arraylist, Linked list allow faster insertion to the map,making it more efficient.


Concurrency Explanation:
___________________________________________
The following data structures were considered when writing the solution (in class FindDuplicateFilesConcurrency):
1. ConcurrentLinkedQueue - allows threads to share access.
2. ConcurrentHashMap - it allows multiple threads in a program to have access to it without locking it. 


Other
_______________________________________
1. The BufferReader was used to retrieve/read the content in the given file. Compared to the Scanner BufferRead is threadsafe and more efficient. 
2. FindDuplicateFileTest - implemented to test FindDuplicateFiles.