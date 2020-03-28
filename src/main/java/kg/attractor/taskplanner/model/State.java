package kg.attractor.taskplanner.model;

public enum  State {
    NEW {
        @Override
        public State nextState(){
            return EXECUTING;
        }
    }
    , EXECUTING {
        @Override
        public State nextState(){
            return COMPLETED;
        }
    }, COMPLETED {
        @Override
        public State nextState() {
            return this;
        }
    };

    public abstract  State nextState();
}
