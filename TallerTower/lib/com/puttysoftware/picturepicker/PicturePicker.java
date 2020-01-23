package com.puttysoftware.picturepicker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.puttysoftware.images.BufferedImageIcon;

public final class PicturePicker {
    /**
     * @version 1.00
     */
    // Fields
    private BufferedImageIcon[] choices;
    private String[] choiceNames;
    private JLabel[] choiceArray;
    private Container pickerContainer;
    private Container choiceContainer;
    private Container radioContainer;
    private Container choiceRadioContainer;
    private ButtonGroup radioGroup;
    private JRadioButton[] radioButtons;
    private JScrollPane scrollPane;
    int index;
    private Color savedSPColor;
    private Color savedPCColor;
    private Color savedCCColor;
    private Color savedRCColor;
    private Color savedCRCColor;
    private Color savedCHColor;
    private EventHandler handler;

    // Constructor
    public PicturePicker(BufferedImageIcon[] pictures, String[] names,
            Color choiceColor) {
        this.handler = new EventHandler();
        this.pickerContainer = new Container();
        this.pickerContainer.setLayout(new BorderLayout());
        this.choiceContainer = new Container();
        this.radioContainer = new Container();
        this.radioGroup = new ButtonGroup();
        this.choiceRadioContainer = new Container();
        this.choiceRadioContainer.setLayout(new BorderLayout());
        this.choiceRadioContainer.add(this.radioContainer, BorderLayout.WEST);
        this.choiceRadioContainer
                .add(this.choiceContainer, BorderLayout.CENTER);
        this.scrollPane = new JScrollPane(this.choiceRadioContainer);
        this.scrollPane
                .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane
                .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.pickerContainer.add(this.scrollPane, BorderLayout.CENTER);
        this.updatePicker(pictures, names);
        this.index = 0;
        this.savedSPColor = this.scrollPane.getBackground();
        this.savedPCColor = this.pickerContainer.getBackground();
        this.savedCCColor = this.choiceContainer.getBackground();
        this.savedRCColor = this.radioContainer.getBackground();
        this.savedCRCColor = this.choiceRadioContainer.getBackground();
        this.savedCHColor = choiceColor;
    }

    // Methods
    public Container getPicker() {
        return this.pickerContainer;
    }

    public void changePickerColor(Color c) {
        this.pickerContainer.setBackground(c);
        this.choiceContainer.setBackground(c);
        this.radioContainer.setBackground(c);
        this.choiceRadioContainer.setBackground(c);
        this.scrollPane.setBackground(c);
        for (int x = 0; x < this.choiceArray.length; x++) {
            this.choiceArray[x].setBackground(c);
            this.radioButtons[x].setBackground(c);
        }
        // Update saved colors
        this.savedSPColor = c;
        this.savedPCColor = c;
        this.savedCCColor = c;
        this.savedRCColor = c;
        this.savedCRCColor = c;
        this.savedCHColor = c;
    }

    public void disablePicker() {
        this.pickerContainer.setEnabled(false);
        this.pickerContainer.setBackground(Color.gray);
        this.choiceContainer.setBackground(Color.gray);
        this.radioContainer.setBackground(Color.gray);
        this.choiceRadioContainer.setBackground(Color.gray);
        this.scrollPane.setBackground(Color.gray);
        for (int x = 0; x < this.radioButtons.length; x++) {
            this.radioButtons[x].setEnabled(false);
        }
    }

    public void enablePicker() {
        this.pickerContainer.setEnabled(true);
        this.pickerContainer.setBackground(this.savedPCColor);
        this.choiceContainer.setBackground(this.savedCCColor);
        this.radioContainer.setBackground(this.savedRCColor);
        this.choiceRadioContainer.setBackground(this.savedCRCColor);
        this.scrollPane.setBackground(this.savedSPColor);
        for (int x = 0; x < this.radioButtons.length; x++) {
            this.radioButtons[x].setEnabled(true);
        }
    }

    public void updatePicker(BufferedImageIcon[] newImages, String[] newNames) {
        this.choices = newImages;
        this.choiceNames = newNames;
        this.choiceContainer.removeAll();
        this.radioContainer.removeAll();
        this.radioButtons = new JRadioButton[this.choices.length];
        this.choiceContainer.setLayout(new GridLayout(this.choices.length, 1));
        this.radioContainer.setLayout(new GridLayout(this.choices.length, 1));
        this.choiceArray = new JLabel[this.choices.length];
        for (int x = 0; x < this.choices.length; x++) {
            this.choiceArray[x] = new JLabel(this.choiceNames[x],
                    this.choices[x], SwingConstants.LEFT);
            this.choiceArray[x].setOpaque(true);
            this.choiceArray[x].setBackground(this.savedCHColor);
            this.choiceContainer.add(this.choiceArray[x]);
            this.radioButtons[x] = new JRadioButton();
            this.radioButtons[x].setOpaque(true);
            this.radioButtons[x].setBackground(this.savedCHColor);
            this.radioButtons[x]
                    .setActionCommand(Integer.valueOf(x).toString());
            this.radioGroup.add(this.radioButtons[x]);
            this.radioButtons[x].addActionListener(this.handler);
            this.radioContainer.add(this.radioButtons[x]);
        }
        this.radioButtons[0].setSelected(true);
    }

    public void setPickerDimensions(int maxHeight) {
        int newPreferredWidth = this.pickerContainer.getLayout()
                .preferredLayoutSize(this.pickerContainer).width
                + this.scrollPane.getVerticalScrollBar().getWidth();
        int newPreferredHeight = Math.min(maxHeight, this.pickerContainer
                .getLayout().preferredLayoutSize(this.pickerContainer).height);
        this.pickerContainer.setPreferredSize(new Dimension(newPreferredWidth,
                newPreferredHeight));
    }

    public void selectLastPickedChoice(int lastPicked) {
        this.radioButtons[lastPicked].setSelected(true);
    }

    /**
     * 
     * @return the index of the picture picked
     */
    public int getPicked() {
        return this.index;
    }

    private class EventHandler implements ActionListener {
        EventHandler() {
            // Do nothing
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            // A radio button
            PicturePicker.this.index = Integer.parseInt(cmd);
        }
    }
}
