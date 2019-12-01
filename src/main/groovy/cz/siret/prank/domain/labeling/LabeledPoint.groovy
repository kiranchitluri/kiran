package cz.siret.prank.domain.labeling

import groovy.transform.CompileStatic
import org.biojava.nbio.structure.Atom
import org.biojava.nbio.structure.Bond
import org.biojava.nbio.structure.Element
import org.biojava.nbio.structure.Group

/**
 * Annotated point used for generating visualizations and pocket predictions.
 */
@CompileStatic
class LabeledPoint implements Atom {

    Atom point //@Delegate

    /**
     * ligandability score histogram - direct output of classifier (hist[0]=unligandable,hist[1]=ligandable)
     * always length=2
     */
    double[] hist  // length=2
    double score = Double.NaN

    boolean predicted
    boolean observed

    /**
     * pocket number <br>
     * 0 = no pocket
     */
    int pocket = 0


    LabeledPoint(Atom point, double[] hist, boolean observed, boolean predicted) {
        this.point = point
        this.hist = hist
        this.predicted = predicted
        this.observed = observed
    }

    LabeledPoint(Atom point) {
        this.point = point
        this.hist = new double[2]
        this.predicted = false
        this.observed = false
    }

    LabeledPoint(Atom point, boolean observed) {
        this.point = point
        this.hist = new double[2]
        this.predicted = false
        this.observed = observed
    }

//===========================================================================================================//

    /**
     * @return predicted lgandability score from interval <0,1> (aggregated from histogram)
     */
    double getLigandabilityScore() {
        hist[1] / (hist[0]+hist[1])
    }

//===========================================================================================================//

    @Override
    public void setName(String s) {
        point.setName(s);
    }

    @Override
    public String getName() {
        return point.getName();
    }

    @Override
    public void setElement(Element element) {
        point.setElement(element);
    }

    @Override
    public Element getElement() {
        return point.getElement();
    }

    @Override
    public void setPDBserial(int i) {
        point.setPDBserial(i);
    }

    @Override
    public int getPDBserial() {
        return point.getPDBserial();
    }

    @Override
    public void setCoords(double[] doubles) {
        point.setCoords(doubles);
    }

    @Override
    public double[] getCoords() {
        return point.getCoords();
    }

    @Override
    public void setX(double v) {
        point.setX(v);
    }

    @Override
    public void setY(double v) {
        point.setY(v);
    }

    @Override
    public void setZ(double v) {
        point.setZ(v);
    }

    @Override
    public double getX() {
        return point.getX();
    }

    @Override
    public double getY() {
        return point.getY();
    }

    @Override
    public double getZ() {
        return point.getZ();
    }

    @Override
    public void setAltLoc(Character character) {
        point.setAltLoc(character);
    }

    @Override
    public Character getAltLoc() {
        return point.getAltLoc();
    }

    @Override
    public void setOccupancy(float v) {
        point.setOccupancy(v);
    }

    @Override
    public float getOccupancy() {
        return point.getOccupancy();
    }

    @Override
    public void setTempFactor(float v) {
        point.setTempFactor(v);
    }

    @Override
    public float getTempFactor() {
        return point.getTempFactor();
    }

    @Override
    public Object clone() {
        return point.clone();
    }

    @Override
    public void setGroup(Group group) {
        point.setGroup(group);
    }

    @Override
    public Group getGroup() {
        return point.getGroup();
    }

    @Override
    public void addBond(Bond bond) {
        point.addBond(bond);
    }

    @Override
    public List<Bond> getBonds() {
        return point.getBonds();
    }

    @Override
    public void setBonds(List<Bond> list) {
        point.setBonds(list);
    }

    @Override
    public boolean hasBond(Atom atom) {
        return point.hasBond(atom);
    }

    @Override
    public short getCharge() {
        return point.getCharge();
    }

    @Override
    public void setCharge(short i) {
        point.setCharge(i);
    }

    @Override
    public String toPDB() {
        return point.toPDB();
    }

    @Override
    public void toPDB(StringBuffer stringBuffer) {
        point.toPDB(stringBuffer);
    }

}
