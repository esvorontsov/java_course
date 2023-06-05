import java.util.ArrayList;
import java.util.List;

    public class SingerBaseDao implements SingerDAO {

        private final ArrayList<Singer> singers = new ArrayList<>();

        @Override
        public List<Singer> getSingers() {
            return this.singers.stream().map(s -> new Singer(s)).toList();
        }

        @Override
        public void setSingers(List<Singer> singers) {
            this.singers.addAll(singers.stream().map(s -> new Singer(s)).toList());
        }
    }
