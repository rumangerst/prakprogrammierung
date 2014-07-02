/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.game;

/**
 *
 * @author ruman
 */
public class InvalidPlayerActionException extends RuntimeException
{
    public InvalidPlayerActionException()
    {
        
    }
    
    public InvalidPlayerActionException(String message)
    {
        super(message);
    }
}
