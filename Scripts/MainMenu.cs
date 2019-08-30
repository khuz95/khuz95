using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using TMPro;

public class MainMenu : MonoBehaviour
{
    
    public TextMeshProUGUI highScore;
   void Start()
    {
        highScore.text = "Highscore: " + PlayerPrefs.GetInt("HighScore", 0).ToString();
        Cursor.lockState = CursorLockMode.None;
        Cursor.visible = true;
    }
   public void PlayGame()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex + 1);
    }

    public void Mute()
    {
        AudioListener.pause = !AudioListener.pause;
    }

    public void QuitGame()
    {
        Debug.Log("QUIT");
        Application.Quit();
    }
}
