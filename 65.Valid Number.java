class Solution {
    public boolean isNumber(String s) {
        return s.matches("[+-]?((\\d+\\.?\\d*)|(\\.\\d+))([eE][+-]?\\d+)?");
    }
}
