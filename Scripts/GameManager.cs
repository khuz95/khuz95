using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameManager : MonoBehaviour
{

    public int currentPickup;

   

    public Text livesText; 

    public Text pickupText;

    public Text jumpText;

    public Text speedText;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void currentLives(int value)
    {
        livesText.text = "Lives: " + value;
    }

    public void AddPickup(int pickupToAdd)
    {
        currentPickup += pickupToAdd;
        pickupText.text = "Score: " + currentPickup;
    }

    public void MinusPickup(int pickupToMinus)
    {
        currentPickup -= pickupToMinus;
        pickupText.text = "Score: " + currentPickup;
    }

    public void superJumpPowerUp()
    {
        jumpText.text = "SUPER JUMP ACTIVATED";
    }

    public void superSpeedPowerUp()
    {
        speedText.text = "SUPER SPEED ACTIVATED";
    }
}
