using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ChangeToEnding : MonoBehaviour
{
    public Animator animator;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
    private void OnTriggerEnter(Collider gameObjectInformation)
    {
        if(gameObjectInformation.gameObject.tag == "Player")
        {
            Debug.Log("Collision Detected");
            if (FindObjectOfType<GameManager>().currentPickup > PlayerPrefs.GetInt("HighScore", 0))
            {
                PlayerPrefs.SetInt("HighScore", FindObjectOfType<GameManager>().currentPickup);
            }
            
            animator.SetTrigger("FadeOut");

        }
    }

    public void OnFadeComplete()
    {
        SceneManager.LoadScene(5);
    }
}
