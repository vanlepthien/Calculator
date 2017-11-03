package org.lepthien.calculator.gwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("calculator")
public interface CalculatorService extends RemoteService, org.lepthien.calculator.gwt.shared.CalculatorService{
}
