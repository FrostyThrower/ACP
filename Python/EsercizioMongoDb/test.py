
string = "cappello-scarpe-occhiali"

items_dict = {}

for i in range(len(string.split('-'))):
    items_dict[f"item-{i}"] = string.split('-')[i]

print(items_dict)