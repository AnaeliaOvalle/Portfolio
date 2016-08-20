import random

def randomPhrase(phrases):
	r=random.randrange(len(phrases))
  	return phrases[r]

def stringasList(randomPhrase):
	gamePhrase=list(randomPhrase)
	return gamePhrase

def listasString(hiddenPhrase):
    partiallyhidden="".join(hiddenPhrase)
    return partiallyhidden

def hiddenPhrase(hiddenPhrase):
	for i in range (0, len(hiddenPhrase)):
            if hiddenPhrase[i]!=" ":
                hiddenPhrase[i]=replacement
        return hiddenphrase

def processGuess(guess,gamePhrase,hiddenPhrase):
	for i in range (0,len(gamePhrase)):
		if guess==gamePhrase[i]:
			hiddenPhrase[i]=guess

	return guess in gamePhrase

def gameWon(hiddenPhrase,gamePhrase):
	if gamePhrase==hiddenPhrase:
	    return True



miss=0
misses=[]
phrases=['PINK PANTHER','COOKIES ROCK','SCOOBY DOO']
randomPhrase=randomPhrase(phrases)
gamePhrase=stringasList(randomPhrase)
hiddenphrase=gamePhrase[:]
replacement="_"
hiddenPhrase=hiddenPhrase(hiddenphrase)

while miss<=8:
	guess=raw_input("Please enter a letter!")
	guess.upper()
	if guess in misses:
	    guess=raw_input("You entered this letter already. Please enter another capital letter!")
	guessprocess=processGuess(guess,gamePhrase,hiddenPhrase)
	partiallyhidden=listasString(hiddenPhrase)
	if guessprocess== False:
		miss= miss+1
		misses.append(guess)
	print partiallyhidden
	if gameWon(hiddenPhrase,gamePhrase)== True:
	    print ("OH MY GOD, YOU WON!")
	    break
	if miss>=8:
	    print ("Sorry, the phrase was:"), "".join(gamePhrase)
	    break
