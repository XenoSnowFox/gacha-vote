package com.xenosnowfox.gachavote.twitch;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * IRC Message based upon <a href="https://datatracker.ietf.org/doc/html/rfc1459">RFC1459</a>.
 */
@Setter
@Getter
@ToString
public class TwitchIRCMessage {
	/**
	 * Returns an IRC Message from the raw string representation.
	 *
	 * @param withMessage
	 * 		Raw IRC Message.
	 * @return IRC Message object.
	 * @throws IllegalArgumentException
	 * 		if the provided message is blank or empty.
	 */
	public static TwitchIRCMessage fromString(@NonNull final String withMessage) {

		if (withMessage.trim().isEmpty()) {
			throw new IllegalStateException("Empty Line.");
		}

		TwitchIRCMessage message = new TwitchIRCMessage();

		String str = withMessage.trim();

		if (str.startsWith(":")) {
			String[] s = str.split(" ", 2);
			message.setPrefix(s[0]);
			str = s[1];
		}

		List<String> args = new ArrayList<>();
		if (str.contains(" :")) {
			String[] s = str.split(" :", 2);

			args.addAll(Arrays.asList(s[0].split(" ")));
			args.add(s[1]);
		} else {
			args.addAll(Arrays.asList(str.split(" ")));
		}

		message.setCommand(args.remove(0));

		String[] arr = new String[args.size()];
		for (int i = 0; i < args.size(); i++) {
			arr[i] = args.get(i);
		}
		message.setArguments(arr);
		return message;
	}

	/**
	 * Message prefix data.
	 */
	private String prefix;

	/**
	 * Command.
	 */
	private String command;

	/**
	 * Command arguments.
	 */
	private String[] arguments;
}
