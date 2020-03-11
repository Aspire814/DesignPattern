package com.storm.action_model.strategy;

public class StrategyFactory {
	private IStrategySrv strategySrv;

	public void executeStrategy() {
		strategySrv.doSomething();
	}

	public StrategyFactory() {
		super();
	}

	public StrategyFactory(int strategyType) {
		super();
		if (strategyType == 1) {
			IStrategySrv newStrategySrv = new Strategy();
			setStrategySrv(newStrategySrv);
		}
	}

	public IStrategySrv getStrategySrv() {
		return strategySrv;
	}

	public void setStrategySrv(IStrategySrv strategySrv) {
		this.strategySrv = strategySrv;
	}

}
