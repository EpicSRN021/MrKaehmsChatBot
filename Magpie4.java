import java.util.Random; 

public class Magpie4
{
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hey, I'm Mr. Kaehms the coolest teacher in school, welcome to my classroom!";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		Random num = new Random();
		if (statement.length() == 0)
		{
			response = "Did you say something?";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why not? I'm starting to think you are mean";
		}
		else if (findKeyword(statement, "mother") >= 0
				|| findKeyword(statement, "father") >= 0
				|| findKeyword(statement, "sister") >= 0
				|| findKeyword(statement, "brother") >= 0)
		{
			response = "Oh interesting; tell me more about your family.";
		}

		else if(findKeyword(statement, "hi") >=0
				|| findKeyword(statement, "hey") >=0
				|| findKeyword(statement, "hello")>=0
				|| findKeyword(statement,"sup")>=0)
		{
			response = "Hey what's up, did you know that I love my Period 5 students the most.";
		}

		else if((findKeyword(statement, "you")>=0 && findKeyword(statement, "doing")>=0)||(findKeyword(statement, "your")>=0 && findKeyword(statement, "day")>=0))
		{
			response = "Great actually. I just checked up on the house I am building, and it is going well";
		}

		// joke responses
		else if(findKeyword(statement, "joke") >=0) {
			int rand = num.nextInt(4);
			if (rand == 0) {
				response = "Let me tell you a joke. Why did the programmer quit his job...because he didn't get arrays (a raise)";
			}
			else if (rand == 1) {
				response = "Ok, here's a joke. What did the Java Class say to the C code...You've got no class";
			}
			else if (rand == 2) {
				response = "Alright, here's a joke. Whats the object-oriented way to become wealthy...Inheritance";
			}
			else {
				response = "Ok I thought of a funny joke. What is a Java programmers favorite musical note...C#";
			}
		}

		else if (findKeyword(statement, "basketball") >= 0 || findKeyword(statement, "NBA") >=0) 
			{
				response = "I'm a huge warriors fan! Strength in Numbers, Dub Nation!";
			}
		
		else if (findKeyword(statement, "sport") >= 0 || findKeyword(statement, "sports") >=0) 
		{
			response = "You watch sports? I love basketball! I used to teach basketball many years back!";
		}

		else if (findKeyword(statement, "hike") >= 0 || findKeyword(statement, "mountains") >=0 ||
		 	findKeyword(statement, "walk") >=0) {
				response = "I love walking or hiking on trails in the mountains. I love the environment!";

			}
		
		else if (findKeyword(statement, "hobby") >= 0 || findKeyword(statement, "pastime") >=0 || findKeyword(statement, "like")
		 >= 0 && findKeyword(statement, "like") >= 0 && findKeyword(statement, "to") >= 0 && findKeyword(statement, "do") >= 0) {
			response = "In my free time, I love to hike and explore nature. It means a lot to me!";
		 }
		
		
	
		
		else if(findKeyword(statement, "nature") >=0 || findKeyword(statement, "environment") >=0 )  {
			response = "I love the environment! Remember to always be safe and appreciate nature. Also, I have a club here in DHS. If you are ever interested, you should join it!";

		}

		else if(findKeyword(statement, "code") >=0 || findKeyword(statement, "coding") >=0 ) {
			response = "Coding is my passion and is so interesting to me! I can help you anytime if you need it.";
		}

		else if(findKeyword(statement, "Java") >=0 )
		{
			response = "Java, what an interesting language.";
		}

		else if(findKeyword(statement, "AI") >=0 )
		{
			response = "AI is such an interesting thing, isnt it? I am truly fascinated by it and it's development to come.";
		}

		else if(findKeyword(statement, "Python") >=0 )
		{
			response = "Python is crazily developing. For you and the new generation, Python will be revolutionary";
		}

		else if(findKeyword(statement, "I") >= 0 || findKeyword(statement, "don't") >=0 || findKeyword(statement, "like")
		>= 0  && findKeyword(statement, "to") >= 0) {
			response = "Don't ever say never. Always keep on trying and try to do new things if you don't like others.";
		}

		// Responses which require transformations
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		
		else
		{
			// Look for a two word (you <something> me)
			// pattern
			int psn = findKeyword(statement, "you", 0);

			if (psn >= 0
					&& findKeyword(statement, "me", psn) >= 0)
			{
				response = transformYouMeStatement(statement);
			}
			else
			{
				response = getRandomResponse();
			}
		}
		return response;
	}
	
	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "What would it mean to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}

	
	
	/**
	 * Take a statement with "you <something> me" and transform it into 
	 * "What makes you think that I <something> you?"
	 * @param statement the user statement, assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfYou = findKeyword (statement, "you", 0);
		int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
		
		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}
	
	

	
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @param startPos the character of the string to begin the search at
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal, int startPos)
	{
		String phrase = statement.trim();
		//  The only change to incorporate the startPos is in the line below
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
		
		//  Refinement--make sure the goal isn't part of a word 
		while (psn >= 0) 
		{
			//  Find the string of length 1 before and after the word
			String before = " ", after = " "; 
			if (psn > 0)
			{
				before = phrase.substring (psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}
			
			//  If before and after aren't letters, we've found the word
			if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
					&& ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
			{
				return psn;
			}
			
			//  The last position didn't work, so let's find the next, if there is one.
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
			
		}
		
		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		final int NUMBER_OF_RESPONSES = 4;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (whichResponse == 0)
		{
			response = "Interesting, tell me more.";
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm.";
		}
		else if (whichResponse == 2)
		{
			response = "Do you really think so?";
		}
		else if (whichResponse == 3)
		{
			response = "You don't say.";
		}

		return response;
	}

}
