package com.hadleym.ws.example;
import javax.xml.bind.annotation.*;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Score {
	public int wins, losses, ties;
}
