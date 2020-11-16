package Interfaces;

import Units.Unit;

import java.util.List;

public interface ITarget {
    boolean CanTarget(Unit unit);
    List<Object> CheckRange();
}
