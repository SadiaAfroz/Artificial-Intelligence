/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aioffline1;

import java.util.ArrayList;
import java.util.List;

public class State {

    //int boatCapacity = 2;
    private int cl, ml, cr, mr, b;
    private State parent;
    public List<State> children;
     /// Left to Right
    ///cl=cannibal_left ml=missionaries_left cr=cannibal_right mr=missionaries_right b=1(left),0(right)
    public State(int cl, int ml, int cr, int mr, int b) {
        this.cl = cl;
        this.ml = ml;
        this.b = b;
        this.cr = cr;
        this.mr = mr;
        children = new ArrayList<State>();
    }

    public boolean isGoal() {
        return cl == 0 && ml == 0;
    }

    public boolean isRight() {
        if (cl >= 0 && ml >= 0 && cr >= 0 && mr >= 0 && (ml == 0 || ml >= cl) && (mr == 0 || mr >= cr)) {
            return true;
        }
        return false;
    }

    public void childrenNodesCreate() {
        
         int j;
            for (int i = 0; i <= MyClass.boatsize; i++) {
                j = 0;
                while (i + j <= MyClass.boatsize) {
                    if (i == 0 && j == 0) {
                        j++;
                        continue;
                    }
                    //System.out.println(i + " , " +   j);
                    if(b==1)
                        Insert(new State(cl-i, ml-j, cr+i, mr+j, 0)); // left to right
                    else
                        Insert(new State(cl+i, ml+j, cr-i, mr-j, 1)); // right to left
                    j++;
                }
            }
        
    

    }

    private void Insert(State newnode) {
        //check and insert
        if (newnode.isRight()) {
            newnode.setParent(this);
            children.add(newnode);
        }
    }

    public int getCl() {
        return cl;
    }

    public int getMl() {
        return ml;
    }

    public void setCl(int cl) {
        this.cl = cl;
    }

    public void setMl(int ml) {
        this.ml = ml;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }

    public void setMr(int mr) {
        this.mr = mr;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public int getCr() {
        return cr;
    }

    public int getMr() {
        return mr;
    }

    public int getB() {
        return b;
    }

    public State getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State)) {
            return false;
        }
        State s = (State) obj;
        return (s.cl == cl && s.ml == ml && s.b == b && s.cr == cr && s.mr == mr);
    }
}
