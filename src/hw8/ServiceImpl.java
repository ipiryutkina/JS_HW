package hw8;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ServiceImpl implements Service {

    @Override
    public List<String> run(String item, double value, Date date) {
        List<String> li = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            li.add("item" + i + ' ' + date.toString());
        }
        return li;
    }

    @Override
    public List<String> work(String item) {
        List<String> li = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            li.add("item" + i);
        }
        return li;
    }

    @Override
    public void doHardWork(String s, int i) {
        String out = s + i;
    }

}