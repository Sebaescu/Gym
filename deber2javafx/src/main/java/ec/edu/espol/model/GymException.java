/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

/**
 *
 * @author sebastian
 */
public class GymException extends Exception{
    public GymException() {
    }
    public GymException(String msj){
        super(msj);
    }
}
