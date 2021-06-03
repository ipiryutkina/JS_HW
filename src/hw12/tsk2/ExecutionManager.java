package hw12.tsk2;

import java.util.List;

public interface ExecutionManager {
    Context execute(Runnable callback, List<Runnable> tasks);
}
