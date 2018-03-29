package com.example.cgz.bloodsoulnote2.ipc.aidl;

import com.example.cgz.bloodsoulnote2.ipc.aidl.Game;

interface IGameManager {
    List<Game> getGameList();
    void addGame(in Game game);
}