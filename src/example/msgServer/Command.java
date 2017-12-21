package com.example.msgServer;

import java.io.IOException;

public interface Command 
{
  public void execute() throws IOException;
}
