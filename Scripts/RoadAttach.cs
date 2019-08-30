using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RoadAttach : MonoBehaviour
{

    public GameObject MovingRoad;
    public GameObject Player;

    private void OnTriggerEnter()
    {
        Player.transform.parent = MovingRoad.transform;
    }
}
