// Package statement is not allowed for implicitly declared class
// package com.lavalliere.daniel.projects;

static int getDefaultValue() {
    return 1;
}

int getAnotherValue() {
    return 2;
}

void main() {
    System.out.printf("Default value: %d AnotherValue: %d", getDefaultValue(), getAnotherValue());
}