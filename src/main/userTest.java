/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
/**
 *
 * @author Calvin
 */
public class userTest {
    
    private User testSubject, testSubject1, testSubject2, testSubject3, testSubject4, testSubject5, testSubject6;
    
    @Before
    public void setUp() throws Exception{
        this.testSubject = new User("reporter", "password"); // existing user that is a reporter
        
        this.testSubject1 = new User("fakeuser", "wrongpassword"); // wrong user wrong password
        
        this.testSubject2 = new User("reporter", "pass"); // correct user wrong password
        
        this.testSubject3 = new User("report", "password"); // correct user wrong password
        
        this.testSubject4 = new User("reviewer", "password"); // existing user that is a reviewer
        
        this.testSubject5 = new User("developer", "password"); // existing user that is a developer
        
        this.testSubject6 = new User("triager", "password"); // existing user that is a triager

        
    }
    
    @After
    public void tearDown() throws Exception 
    {
        testSubject = null;
        testSubject1 = null;
        testSubject2 = null;
        testSubject3 = null;
        testSubject4 = null;
        testSubject5 = null;
        testSubject6 = null;
    }
    
    @Test
    public void testLogin()
    {
        //existing user
        assertEquals("reporter", testSubject.getUser());
        assertEquals("password", testSubject.getPassword());
        assertEquals(51, testSubject.getAccountID());
        assertEquals("br", testSubject.getUserType());
        
        //non-existing user
        assertEquals("fakeuser", testSubject1.getUser());
        assertEquals("wrongpassword", testSubject1.getPassword());
        assertEquals(0, testSubject1.getAccountID());
        assertEquals(null, testSubject1.getUserType());

        //correct user wrong password
        assertEquals("reporter", testSubject2.getUser());
        assertEquals("pass", testSubject2.getPassword());
        assertEquals(0, testSubject2.getAccountID());
        assertEquals(null, testSubject2.getUserType());
        
        //wrong user correct password
        assertEquals("report", testSubject3.getUser());
        assertEquals("password", testSubject3.getPassword());
        assertEquals(0, testSubject3.getAccountID());
        assertEquals(null, testSubject3.getUserType());
         
        //existing user that is a reviewer
        assertEquals("reviewer", testSubject4.getUser());
        assertEquals("password", testSubject4.getPassword());
        assertEquals(53, testSubject4.getAccountID());
        assertEquals("rev", testSubject4.getUserType());
        
        //existing user that is a developer
        assertEquals("developer", testSubject5.getUser());
        assertEquals("password", testSubject5.getPassword());
        assertEquals(52, testSubject5.getAccountID());
        assertEquals("dev", testSubject5.getUserType());
        
        //existing user that is a triager
        assertEquals("triager", testSubject6.getUser());
        assertEquals("password", testSubject6.getPassword());
        assertEquals(54, testSubject6.getAccountID());
        assertEquals("tri", testSubject6.getUserType());
    }
}
