using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RoadUnlink : MonoBehaviour
{
    public GameObject Player;

    private void OnTriggerEnter()
    {
        Player.transform.parent = null;
    }
}
