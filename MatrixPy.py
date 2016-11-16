import time as t
import sys
"""------------------------------------------------
#	@author	-	Kyle Truebody
#	Testing the overheads and cost of python lists 
#	by multiplying two matrice together. 
#	
-------------------------------------------------"""

# Populate the matrices as a identity matrix
#
#	@param int A - The matrix to populate
#
def populate(A):
	for x in range (len(A)):
		for y in range (len(A[0])):
			if x==y:
				A[x][y]=1
			else:
				A[x][y]=0
	return A	

# Create the matrix dimensions
#
#	@param int dim 		- col & row size of matrix
#	@return int a[[]] 	- the layout of the matrix
# 
def make2dList(dim):
    a=[]
    for row in xrange(dim):a += [[0]*dim]

    return a

# Multiply the two matrices together.
#
#	@param int matrix1[[]]	- first subject
#	@param int matrix2[[]]	- second subject
#	@param int result[[]]	- resulting subject
#
def matrixMulti(matrix1,matrix2,result):
	
	for frstM in range (len(matrix1)):
		for secM in range (len(matrix2[0])):
			for k in range (len(matrix2)):
				result[frstM][secM] += matrix1[frstM][k] * matrix2[k][secM]
	return result

# Write to .dat file
#
#	@param String results	- the recorded time and epoch results
#	@param File target		- file name/path
#
def writeToFile(results,target):
	target.write(results+"\n")

# ------------------ Main Opertation-------------------------------#
# 	
#	@param File target 		- creates file to store results
#	@param int rows_cols 	- matrix dimensions
#	@param double startTime	- start time of transaction in seconds (microseconds accuracy)
#	@param double stopTime 	- end time of transaction in seconds (microseconds accuracy)
#	@param double rTime		- resulting time of transaction in seconds (mircosecond accuracy)
#	@param String results	- results converted to string

# File name to save results	
target = open("pythonTest.dat",'w')
rows_cols = 0

for num in range(0,11):
	
	a = make2dList(rows_cols)
	b = make2dList(rows_cols)
	result = make2dList(rows_cols)

	matrix1 = populate(a)
	matrix2 = populate(b)

	#multiplying the matrices and timing opreration
	startTime = t.time()
	result = matrixMulti(matrix1,matrix2,result)
	stopTime = t.time()
	rTime = stopTime - startTime

	results = str(rows_cols) + "\t" + str(rTime)
	print rows_cols, rTime
	writeToFile(results,target)
	rows_cols += 100

print "Complete!"

#--------------------------end--------------------------------------#