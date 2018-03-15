
package com.spring.statemachine.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.statemachine.ObjectStateMachine;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.event.OnStateEntryEvent;
import org.springframework.statemachine.event.OnStateExitEvent;
import org.springframework.statemachine.event.OnTransitionEvent;
import org.springframework.statemachine.event.StateMachineEvent;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.TransitionKind;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@ShellComponent
public class StateMachineCommands {

    private StateMachine<States, Events> stateMachine;

    @Autowired
    private AnnotationConfigApplicationContext context;

    @ShellMethod(value = "Sends an event to a state machine", key = "sm select")
    public String select(final String name) {
        if (stateMachine != null) {
            return "have selected sm " ;
        }
        stateMachine = context.getBean(name, ObjectStateMachine.class);
        return "select sm :" + name;
    }

    @ShellMethod(value = "Sends an event to a state machine", key = "sm event")
    public String event(final Events event) {
        if (stateMachine == null) {
            return "please select sm";
        }
        getStateMachine().sendEvent(event);
        return "Event " + event + " send";
    }

    protected StateMachine<States, Events> getStateMachine() {
        return stateMachine;
    }

    @ShellMethod(value = "sm state", key = "sm state")
    public String state() {
        if (stateMachine == null) {
            return "please select sm";
        }
        State<States, Events> state = stateMachine.getState();
        if (state != null) {
            return StringUtils.collectionToCommaDelimitedString(state.getIds());
        } else {
            return "No state";
        }
    }

    @ShellMethod(value = "sm start", key = "sm start")
    public String start() {
        if (stateMachine == null) {
            return "please select sm";
        }
        stateMachine.start();
        return "State machine started";
    }

    @ShellMethod(value = "sm stop", key = "sm stop")
    public String stop() {
        if (stateMachine == null) {
            return "please select sm";
        }
        stateMachine.stop();
        return "State machine stopped";
    }


    @ShellMethod(value = "sm variables", key = "sm variables")
    public String variables() {
        if (stateMachine == null) {
            return "please select sm";
        }
        StringBuilder buf = new StringBuilder();
        Set<Map.Entry<Object, Object>> entrySet = stateMachine.getExtendedState().getVariables().entrySet();
        Iterator<Map.Entry<Object, Object>> iterator = entrySet.iterator();
        if (entrySet.size() > 0) {
            while (iterator.hasNext()) {
                Map.Entry<Object, Object> e = iterator.next();
                buf.append(e.getKey() + "=" + e.getValue());
                if (iterator.hasNext()) {
                    buf.append("\n");
                }
            }
        } else {
            buf.append("No variables");
        }
        return buf.toString();
    }


    @Bean
    public TestEventListener testEventListener() {
        return new TestEventListener();
    }

    static class TestEventListener implements ApplicationListener<StateMachineEvent> {

        @Override
        public void onApplicationEvent(StateMachineEvent event) {
            if (event instanceof OnStateEntryEvent) {
                OnStateEntryEvent e = (OnStateEntryEvent) event;
                System.out.println("Entry state " + e.getState().getId());
            } else if (event instanceof OnStateExitEvent) {
                OnStateExitEvent e = (OnStateExitEvent) event;
                System.out.println("Exit state " + e.getState().getId());
            } else if (event instanceof OnTransitionEvent) {
                OnTransitionEvent e = (OnTransitionEvent) event;
                if (e.getTransition().getKind() == TransitionKind.INTERNAL) {
                    System.out.println("Internal transition source=" + e.getTransition().getSource().getId());
                }
            }
        }
    }


}