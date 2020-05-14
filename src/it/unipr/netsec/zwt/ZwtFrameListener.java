package it.unipr.netsec.zwt;



/** Listener of ZwtFrame events.
 */
public interface ZwtFrameListener {
	
	/** Invoked when the user attempts to close the window from the window's system menu.
	 * <p>
	 * If implemented, you are supposed to call also the {@link ZwtFrame#dispose()}.
	 * @param frame the frame */
	public void onFrameClosing(ZwtFrame frame);

}
